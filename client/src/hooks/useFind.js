import { useState, useEffect } from 'react';
import { sendAPIRequest, getOriginalServerUrl, isFeatureImplemented, isRequestNotSupported } from '../utils/restfulAPI';

export function useFind(match) {
    const [results, setResults] = useState([]);

    const findActions = {
        setResults: setResults
    }

    useEffect(
        () => {makeFindRequest(match, findActions)}
    ,[match])

    return {
        results
    };
}

async function makeFindRequest(match, findActions) {
    if (match != '' && match.length >= 3){
        const { setResults } = findActions;

        const defaultLimit = 10; 
        const requestBody = { requestType: 'find', match: match, limit: defaultLimit };
        
        //We have feature implemented:
        const serverUrl = getOriginalServerUrl();
        const findResponse = await sendAPIRequest(requestBody, serverUrl);
        setResults(findResponse.places);
    }
}