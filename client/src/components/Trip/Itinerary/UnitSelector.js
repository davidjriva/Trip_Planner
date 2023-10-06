import React from 'react';

const UnitsSelector = () => {
    const [selectedUnit, setSelectedUnit] = React.useState('miles');
    const units = ['miles', 'kilometers', 'nautical miles'];

    const handleUnitsChange = (e) => {
        setSelectedUnit(e.target.value);
    };

    return (
        <select value={selectedUnit} onChange={handleUnitsChange}>
            {units.map((unit) => (
                <option key={unit} value={unit}>
                    {unit}
                </option>
            ))}
        </select>
    );
};

export default UnitsSelector;