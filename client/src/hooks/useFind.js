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
    if (match.length >= 3){
        const defaultLimit = 10; 
        const requestBody = { requestType: 'find', match: match, limit: defaultLimit };
        makeRequest(findActions, requestBody);
    }

    else if (match === 1 || match === 0)
    {
        const defaultLimit = 1; 
        const requestBody = { requestType: 'find', match: "", limit: defaultLimit };
        makeRequest(findActions, requestBody);
    }
}

async function makeRequest(findActions, requestBody)
{
        const { setResults } = findActions;
        const serverUrl = getOriginalServerUrl();
        const findResponse = await sendAPIRequest(requestBody, serverUrl);
        setResults(findResponse.places);
}