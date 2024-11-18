import React, { useEffect, useState } from 'react';
import { getFunds } from '../lib/api';

const FundList = () => {
  const [funds, setFunds] = useState([]);

  useEffect(() => {
    getFunds().then((response) => setFunds(response.data)).catch(console.error);
  }, []);

  return (
    <div className="fund-list">
      <h2>Available Funds</h2>
      <ul>
        {funds.map((fund) => (
          <li key={fund.id}>
            <strong>{fund.nombre}</strong> - Minimum Investment: ${fund.montoMinimo}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FundList;
