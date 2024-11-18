import React, { useState } from 'react';
import { createUser } from '../lib/api';

const UserCreation = () => {
  const [user, setUser] = useState({ nombre: '', apellido: '', saldo: 0, email: '', telefono: '' });

  const handleSubmit = (e) => {
    e.preventDefault();
    createUser(user).then(() => alert('User created successfully')).catch(console.error);
  };

  return (
    <form onSubmit={handleSubmit} className="user-creation">
      <h2>Create New User</h2>
      <input type="text" placeholder="First Name" onChange={(e) => setUser({ ...user, nombre: e.target.value })} />
      <input type="text" placeholder="Last Name" onChange={(e) => setUser({ ...user, apellido: e.target.value })} />
      <input type="number" placeholder="Balance" onChange={(e) => setUser({ ...user, saldo: e.target.value })} />
      <input type="email" placeholder="Email" onChange={(e) => setUser({ ...user, email: e.target.value })} />
      <input type="text" placeholder="Phone" onChange={(e) => setUser({ ...user, telefono: e.target.value })} />
      <button type="submit">Create User</button>
    </form>
  );
};

export default UserCreation;
