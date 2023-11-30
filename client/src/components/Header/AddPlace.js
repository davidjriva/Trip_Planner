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
	const [refresh, setRefresh] = useState(false);

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
		refresh,
		setRefresh,
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
			Add or Search for a Place
		</ModalHeader>
	);
}

function PlaceSearch(props) {
	const [checkedResults, setCheckedResults] = useState(new Array(props.results.results.length).fill(false));

	const handleClick = (index) => {
		if (!checkedResults[index]) {
			props.append(props.results.results[index]);
		}
		const updatedCheckedResults = [...checkedResults];
		updatedCheckedResults[index] = true;
		setCheckedResults(updatedCheckedResults);
	};
	
	useEffect(() => {
		verifyCoordinates(props);
	}, [props.coordString]);

	const renderResults = () => {
		if (!props.results.results || props.results.results.length === 0) {
			return null;
		}
		
		return (
				<div>
					{props.results.results.map((place, index) => (
						<div key={index} style={{ display: "flexbox", width: "100%", justifyContent: 'space-between' }}>
							<strong>
								{place.name}
							</strong>
							<div>
								{`${place.municipality}, ${place.region}, ${place.country}`} 
							</div>
							<div> 
								{`${parseFloat(place.latitude).toFixed(4)}, ${parseFloat(place.longitude).toFixed(4)}`} 
							</div>
							<Button
								color="primary"
								onClick={() => handleClick(index)}
							>
								{ checkedResults[index] ? "✓" : "+" }
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
						onChange={(input) => {
							props.setCoordString(input.target.value)
							setCheckedResults(new Array(props.results.results.length).fill(false))
						}}
						placeholder='Enter Search Or Coordinates (e.g. -50.0 [long], 100.0 [lat])'
						data-testid='coord-input'
						value={props.coordString}
					/>
					<Button data-testid='home-button' onClick={() => props.append(DEFAULT_STARTING_POSITION)}>
						<FaHome/>
					</Button>
				</InputGroup>
				<Col>
					<Button
						data-testid='random-button'
						color="primary"
						onClick= {() => { setCheckedResults(new Array(1).fill(false)); randomPlace(props); }}
					>
						Random
					</Button>
				</Col>
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

async function verifyCoordinates(props) {
	try {
		const latLngPlace = new Coordinates(props.coordString);
		const lat = latLngPlace.getLatitude();
		const lng = latLngPlace.getLongitude();
		if (isLatLngValid(lat,lng)) {
			const fullPlace = await reverseGeocode({ lat, lng });
			props.setFoundPlace(fullPlace);
		}
	} catch (error) {
		if (props.serverSettings.serverUrl != "testing" && props.coordString.length >= 3) {
			props.setMatch(props.coordString);
		}
		props.setFoundPlace(undefined);
	}
}

function isLatLngValid(lat,lng) {
	return (lat !== undefined && lng !== undefined);
}

function randomPlace(props) 
{

	if (props.refresh){
		props.setMatch(0);
	}
	else{
		props.setMatch(1);
	}
	props.setRefresh(!props.refresh);
	props.setCoordString("");
}
