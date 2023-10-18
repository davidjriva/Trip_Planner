import React, { useEffect, useState } from 'react';
import {
	Button,
	Col,
	Modal,
	ModalBody,
	ModalHeader,
	Input,
	InputGroup,
	Collapse,
	ModalFooter,
} from 'reactstrap';
import { FaHome } from 'react-icons/fa';
import Coordinates from 'coordinate-parser';
import { DEFAULT_STARTING_POSITION } from '../../utils/constants';
import { reverseGeocode } from '../../utils/reverseGeocode';
import { FaSearch } from 'react-icons/fa';
import { getOriginalServerUrl, sendAPIRequest } from '../../utils/restfulAPI';

export default function AddPlace(props) {
	const [foundPlace, setFoundPlace] = useState();
	const [coordString, setCoordString] = useState('');
	const [match, setMatch] = useState('');

	const addPlaceProps = {
		foundPlace,
		setFoundPlace,
		coordString,
		setCoordString,
		append: props.placeActions.append,
		match,
		setMatch
	};

	return (
		<Modal isOpen={props.showAddPlace} toggle={props.toggleAddPlace}>
			<AddPlaceHeader toggleAddPlace={props.toggleAddPlace} />
			<PlaceSearch {...addPlaceProps}/>
			<AddPlaceFooter
				{...addPlaceProps}
			/>
			<FindSearchBar {...addPlaceProps}/>
		</Modal>
	);
}

function FindSearchBar(props) {	
    return (
		<ModalBody>
			<Col>
				<InputGroup>
					<Input
						onChange={(input) => props.setMatch(input.target.value)}
						placeholder='Enter A Name To Match...'
						value={props.match}
					/>
					{ props.match.length >= 3 ?
					(<Button onClick={() => sendFindRequest(props.match)}>
						<FaSearch/>
					</Button>)
					:  (<></>) }
				</InputGroup>
			</Col>
		</ModalBody>
    );
};

function AddPlaceHeader(props) {
	return (
		<ModalHeader className='ml-2' toggle={props.toggleAddPlace}>
			Add a Place
		</ModalHeader>
	);
}

function PlaceSearch(props) {
	useEffect(() => {
		verifyCoordinates(props.coordString, props.setFoundPlace);
	}, [props.coordString]);

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
				<PlaceInfo foundPlace={props.foundPlace} />
			</Col>
		</ModalBody>
	);
}

function PlaceInfo(props) {
	return (
		<Collapse isOpen={!!props.foundPlace}>
			<br />
			{props.foundPlace?.formatPlace()}
		</Collapse>
	);
}

function AddPlaceFooter(props) {
	return (
		<ModalFooter>
			<Button
				color='primary'
				onClick={() => {
					props.append(props.foundPlace);
					props.setCoordString('');
				}}
				data-testid='add-place-button'
				disabled={!props.foundPlace}
			>
				Add Place
			</Button>
		</ModalFooter>
	);
}

async function verifyCoordinates(coordString, setFoundPlace) {
	try {
		const latLngPlace = new Coordinates(coordString);
		const lat = latLngPlace.getLatitude();
		const lng = latLngPlace.getLongitude();
		if (isLatLngValid(lat,lng)) {
			const fullPlace = await reverseGeocode({ lat, lng });
			setFoundPlace(fullPlace);
		}
	} catch (error) {
		setFoundPlace(undefined);
	}
}

function isLatLngValid(lat,lng) {
	return (lat !== undefined && lng !== undefined);
}

async function sendFindRequest(searchMatch) {
	const serverUrl = getOriginalServerUrl();
	const defaultLimit = 10; 
	const requestBody = { requestType: 'find', match: searchMatch, limit:defaultLimit }; 

	const findResponse = await sendAPIRequest(requestBody,serverUrl);
        if (findResponse) {
            return findResponse;
        } else {
            showMessage(`Search request to ${serverUrl} failed. Check the log for more details.`, 'error');
        }

	return '';
}