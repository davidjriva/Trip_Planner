import React, { useState } from 'react';
import { useToggle } from '../../../hooks/useToggle';
import { useDistances } from '../../../hooks/useDistances';
import { Table, Collapse, Button } from 'reactstrap';
import { latLngToText, placeToLatLng } from '../../../utils/transformers';
import { BsChevronDown } from 'react-icons/bs';
import PlaceActions from './PlaceActions';
import Distances from './Distances';
import UnitsSelector from './UnitsSelector';
import { sendAPIRequest, getOriginalServerUrl } from '../../../utils/restfulAPI';
import { Place } from '../../../models/place.model';

const unitsToEarthRadius = {
	miles: 3959,
	kilometers: 6371,
	'nautical miles': 3440,
};

function unitChangeHelper(newUnits, setDistanceUnits, setEarthRadius){
	setDistanceUnits(newUnits);
	if (unitsToEarthRadius.hasOwnProperty(newUnits)) {
		setEarthRadius(unitsToEarthRadius[newUnits]);
	}
}

export default function Itinerary(props) {
	const [earthRadius, setEarthRadius] = useState(3959);
	const [distanceUnits, setDistanceUnits] = useState("miles");
	const { distances } = useDistances(props.places, earthRadius, props.serverSettings);

	const handleUnitsChange = (newUnits) => { unitChangeHelper(newUnits, setDistanceUnits, setEarthRadius) };

	const unitsProps = { earthRadius, setEarthRadius, distanceUnits, setDistanceUnits, handleUnitsChange }
	const placeListProps = { places: props.places, distances: distances, placeActions: props.placeActions, selectedIndex: props.selectedIndex }
	
	return (
		<Table responsive>
			<TripHeader
				{...unitsProps}
				{...placeListProps}
				tripName={props.tripName}
			/>
			<PlaceList 
				{...placeListProps}
			/>
		</Table>
	);
}

function TripHeader(props) {
	return (
		<thead>
			<tr>
				<th
					className='trip-header-title'
					data-testid='trip-header-title'
				>
					{props.tripName} is <Distances distance={props.distances.total}/> <UnitsSelector {...props}/>
				</th>
				<td>
				<Button className="optimizeButton" onClick={() => makeTourRequest(props) }> Optimize </Button>
				</td>
				<td>
				<Button className="optimizeButton" onClick={() => {if (confirm('This will clear ALL places. Are you sure you wish to continue?')) {props.placeActions.removeAll()}}}> Clear </Button>
				</td>
			</tr>
			<tr>
				<td className='places'>
					Place
				</td>
				<td className='leg'>
					Leg
				</td>
				<td className='cumulative'>
					Cumulative
				</td>
			</tr>
		</thead>
	);
}

function PlaceList(props) {
	return (
		<tbody>
			{props.places.map((place, index) => (
				<PlaceRow
					{...props}
					key={`table-${JSON.stringify(place)}-${index}`}
					place={place}
					index={index}
				/>
			))}
		</tbody>
	);
}

function PlaceRow(props) {
	const [showFullName, toggleShowFullName] = useToggle(false);
	const name = props.place.defaultDisplayName;
	const location = latLngToText(placeToLatLng(props.place));
	return (
		<tr className={props.selectedIndex === props.index ? 'selected-row' : ''}>
			<td
				data-testid={`place-row-${props.index}`}
				onClick={() =>
					placeRowClicked(
						toggleShowFullName,
						props.placeActions.selectIndex,
						props.index
					)
				}
			>
				<strong>{name}</strong>
				<AdditionalPlaceInfo {...props} showFullName={showFullName} location={location}/>
			</td>
			<td align={'right'}><Distances distance={props.distances.leg[props.index] || 0}/></td>
			<td align={'right'}><Distances distance={props.distances.cumulative[props.index] || 0}/></td>
			<RowArrow toggleShowFullName={toggleShowFullName} index={props.index}/>
		</tr>
	);
}

function AdditionalPlaceInfo(props) {
	return (
		<Collapse isOpen={props.showFullName}>
			{props.place.formatPlace().replace(`${props.place.defaultDisplayName}, `, '')}
			<br />
			{props.location}
			<br />
			<PlaceActions placeActions={props.placeActions} index={props.index} />
		</Collapse>
	);
}

function placeRowClicked(toggleShowFullName, selectIndex, placeIndex) {
	toggleShowFullName();
	selectIndex(placeIndex);
}

function RowArrow(props) {
	return (
		<td>
			<BsChevronDown data-testid={`place-row-toggle-${props.index}`} onClick={props.toggleShowFullName}/>
		</td>
	);
}

async function makeTourRequest(props) {
	const defaultResponse = 1; 
	const requestBody = { requestType: 'tour', earthRadius: props.earthRadius, response: defaultResponse, places: props.places };
	const serverUrl = getOriginalServerUrl();
	
	try{
		const tourPlaces = await sendAPIRequest(requestBody, serverUrl);
		const newPlaces = tourPlaces.places.map(place =>{
			return new Place(place);
		});
		props.placeActions.setPlaces(newPlaces);
	} catch (error) {
		console.error("Error in makeTourRequest(): ", error);
	}
}
