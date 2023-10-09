import React from 'react';

const UnitsSelector = ({ earthRadius, setEarthRadius, distanceUnits, setDistanceUnits, handleUnitsChange }) => {
    const units = ['miles', 'kilometers', 'nautical miles'];

    return (
        <select value={distanceUnits} onChange={(e) => handleUnitsChange(e.target.value)}>
            {units.map((unit) => (
                <option key={unit} value={unit}>
                    {unit}
                </option>
            ))}
        </select>
    );
};

export default UnitsSelector;