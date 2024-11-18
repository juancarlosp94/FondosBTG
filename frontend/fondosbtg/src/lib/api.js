import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

export const getFunds = () => axios.get(`${API_BASE_URL}/fondos`);
export const createUser = (user) => axios.post(`${API_BASE_URL}/usuarios/crear`, user);
export const deleteUser = (userId) => axios.delete(`${API_BASE_URL}/usuarios/eliminar/${userId}`);
export const subscribeToFund = (userId, fundId) =>
  axios.post(`${API_BASE_URL}/usuarios/${userId}/suscribir/${fundId}`);
export const cancelSubscription = (userId, fundId) =>
  axios.delete(`${API_BASE_URL}/usuarios/${userId}/cancelar/${fundId}`);
