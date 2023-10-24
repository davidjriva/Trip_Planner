import React, { useEffect, useState } from 'react';
import {
	Button,
	Col,
	Modal,
	ModalBody,
	ModalHeader,
	Input,
	InputGroup,
	Collapse
} from 'reactstrap';
import { FaHome } from 'react-icons/fa';
import Coordinates from 'coordinate-parser';
import { DEFAULT_STARTING_POSITION } from '../../utils/constants';
import { reverseGeocode } from '../../utils/reverseGeocode';
import { useFind } from '../../hooks/useFind';

export default function AddPlace(props) {
	const [foundPlace, setFoundPlace] = useState();
	const [coordString, setCoordString] = useState('');
	const [match, setMatch] = useState('');

	const results = useFind(match);
	
	const addPlaceProps = {
		foundPlace,
		setFoundPlace,
		coordString,
		setCoordString,
		append: props.placeActions.append,
		match,
		setMatch,
		results,
		serverSettings: props.serverSettings
	};

	return (
		<Modal isOpen={props.showAddPlace} toggle={props.toggleAddPlace}>
			<AddPlaceHeader toggleAddPlace={props.toggleAddPlace} />
			<PlaceSearch {...addPlaceProps}/>
		</Modal>
	);
}

function AddPlaceHeader(props) {
	return (
		<ModalHeader className='ml-2' toggle={props.toggleAddPlace}>
			Add a Place
		</ModalHeader>
	);
}

function ResultsList({ results, checkedResults, toggleResult }) {
	if (!results.results || results.results.length === 0){
		return null;
	}

	return (
		<div>
			{results.results.map((place, index) => {
				<div key={index} style ={{ display: "flex", justifyContent: "space-between" }}>
					<div> {place.name} </div>
					<Button
						color="primary"
						onClick={() => toggleResults(index)}
					>
						{checkedResults[index] ? "✓" : "+"}
					</Button>	
				</div>
			})}
		</div>
	)
}

function PlaceSearch(props) {
	const [checkedResults, setCheckedResults] = useState(new Array(props.results.results.length).fill(false));

	useEffect(() => {
		verifyCoordinates(props.coordString, props.setFoundPlace, props.setMatch, props.serverSettings);
	}, [props.coordString]);


	const renderResults = () => {
		if (!props.results.results || props.results.results.length === 0) {
			return null;
		}
		
		return (
				<div>
				{props.results.results.map((place, index) => (
					<div key={index} style={{ display: "flex", justifyContent: 'space-between' }}>
					<div>{place.name}</div>
					<Button
						color="primary"
						onClick={() => {
						if (!checkedResults[index]) {
							props.append(props.results.results[index]);
						}
						const updatedCheckedResults = [...checkedResults];
						updatedCheckedResults[index] = true;
						setCheckedResults(updatedCheckedResults);
						}}
					>
						{checkedResults[index] ? "✓" : "+"}
					</Button>
					</div>
				))}
				</div>
			);
	};

	return (
		<ModalBody>
			<Col>
				<InputGroup>
					<Input
						onChange={(input) => props.setCoordString(input.target.value)}
						placeholder='Enter Place Coordinates'
						data-testid='coord-input'
						value={props.coordString}
					/>
					<Button data-testid='home-button' onClick={() => props.append(DEFAULT_STARTING_POSITION)}>
						<FaHome/>
					</Button>
				</InputGroup>
				<PlaceInfo append = {props.append} setCoordString = {props.setCoordString} foundPlace={props.foundPlace} />
				{ renderResults() }
			</Col>
		</ModalBody>
	);
}

function PlaceInfo(props) {
	return (
		<Collapse isOpen={!!props.foundPlace}>
			<br />
			<div style={{ display: "flex", justifyContent: 'space-between' }}>
				{props.foundPlace?.formatPlace()}
				<Button
					color='primary'
					onClick={() => {
						props.append(props.foundPlace);
						props.setCoordString('');
					}}
					data-testid='add-place-button'
					disabled={!props.foundPlace}
				>
				+
				</Button>
			</div>
		</Collapse>
	);
}

async function verifyCoordinates(coordString, setFoundPlace, setMatch, serverSettings) {
	try {
		const latLngPlace = new Coordinates(coordString);
		const lat = latLngPlace.getLatitude();
		const lng = latLngPlace.getLongitude();
		if (isLatLngValid(lat,lng)) {
			const fullPlace = await reverseGeocode({ lat, lng });
			setFoundPlace(fullPlace);
		}
	} catch (error) {
		if (serverSettings.serverUrl != "testing" && coordString.length >= 3) {
			setMatch(coordString);
		}
		setFoundPlace(undefined);
	}
}

function isLatLngValid(lat,lng) {
	return (lat !== undefined && lng !== undefined);
}
