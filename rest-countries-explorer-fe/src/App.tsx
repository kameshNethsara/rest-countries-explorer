import { useState, useEffect } from 'react';
import { type Country } from './types';
import './App.css';

function App() {
  const [countries, setCountries] = useState<Country[]>([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCountry, setSelectedCountry] = useState<Country | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchCountries();
  }, []);

  const fetchCountries = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/countries');
      if (!response.ok) throw new Error('Network response was not ok');
      const data = await response.json();
      setCountries(data);
    } catch (error) {
      console.error("Error fetching countries:", error);
    } finally {
      setLoading(false);
    }
  };

  const filteredCountries = countries.filter(country => 
    country.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="app-container">
      <h1 className="header">🌍 Global Country Explorer</h1>
      
      {/* Search Input */}
      <input 
        type="text" 
        className="search-input"
        placeholder="Search for a country..." 
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      {/* Countries Table */}
      <div className="table-container">
        {loading ? (
          <p style={{ textAlign: 'center' }}>Loading countries...</p>
        ) : (
          <table className="countries-table">
            <thead>
              <tr>
                <th>Flag</th>
                <th>Name</th>
                <th>Capital</th>
                <th>Region</th>
                <th>Population</th>
              </tr>
            </thead>
            <tbody>
              {filteredCountries.map((country, index) => (
                <tr key={`${country.name}-${index}`} onClick={() => setSelectedCountry(country)}>
                  <td><img src={country.flag} alt={`${country.name} flag`} className="flag-img" /></td>
                  <td><strong>{country.name}</strong></td>
                  <td>{country.capital}</td>
                  <td>{country.region}</td>
                  <td>{country.population.toLocaleString()}</td>
                </tr>
              ))}
              {filteredCountries.length === 0 && (
                <tr>
                  <td colSpan={5} style={{ textAlign: 'center', padding: '20px' }}>No countries found.</td>
                </tr>
              )}
            </tbody>
          </table>
        )}
      </div>

      {/* Country Details Modal */}
      {selectedCountry && (
        <div className="modal-overlay" onClick={() => setSelectedCountry(null)}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <h2 className="modal-title">{selectedCountry.name}</h2>
            <div style={{ textAlign: 'center', marginBottom: '20px' }}>
              <img src={selectedCountry.flag} alt="flag" style={{ width: '150px', borderRadius: '8px' }} />
            </div>
            <p className="modal-detail"><strong>🏙️ Capital:</strong> {selectedCountry.capital}</p>
            <p className="modal-detail"><strong>📍 Region:</strong> {selectedCountry.region}</p>
            <p className="modal-detail"><strong>👥 Population:</strong> {selectedCountry.population.toLocaleString()}</p>
            
            <button className="close-btn" onClick={() => setSelectedCountry(null)}>
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;