import React, { useState, useEffect } from 'react';

function DynamicDropdownFromAPI() {
  const [selectedValue, setSelectedValue] = useState('');
  const [dataOptions, setDataOptions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Replace with your actual API endpoint
        const response = await fetch('http://localhost:8080/countries');
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        setDataOptions(data); // Assuming data is an array of objects with label/value
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []); // Empty dependency array means this runs once on mount

  const handleDropdownChange = (event) => {
    setSelectedValue(event.target.value);
  };

  if (loading) return <div>Loading options...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div>
      <label htmlFor="api-select">Select an item:</label>
      <select id="api-select" value={selectedValue} onChange={handleDropdownChange}>
        <option value="">-- Please choose an item --</option>
        {dataOptions.map((item) => (
          <option key={item.value} value={item.value}>
            {item.label}
          </option>
        ))}
      </select>
      {selectedValue && <p>You selected: {selectedValue}</p>}
    </div>
  );
}

export default DynamicDropdownFromAPI;