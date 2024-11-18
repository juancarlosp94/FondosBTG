import React, { useState } from 'react';
import { subscribeToFund, cancelSubscription } from '../lib/api';

const SubscriptionManagement = () => {
  const [userId, setUserId] = useState('');
  const [fundId, setFundId] = useState('');

  const handleSubscribe = () => {
    subscribeToFund(userId, fundId).then(() => alert('Successfully subscribed to the fund')).catch(console.error);
  };

  const handleCancel = () => {
    cancelSubscription(userId, fundId).then(() => alert('Subscription cancelled successfully')).catch(console.error);
  };

  return (
    <div className="subscription-management">
      <h2>Manage Subscription</h2>
      <input type="text" placeholder="User ID" onChange={(e) => setUserId(e.target.value)} />
      <input type="text" placeholder="Fund ID" onChange={(e) => setFundId(e.target.value)} />
      <button onClick={handleSubscribe}>Subscribe to Fund</button>
      <button onClick={handleCancel}>Cancel Subscription</button>
    </div>
  );
};

export default SubscriptionManagement;
