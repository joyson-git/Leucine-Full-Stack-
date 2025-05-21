// src/services/api.js
import axios from 'axios';

const API_URL = 'http://localhost:8083'; // Update with your backend URL

export const fetchTodos = () => axios.get(`${API_URL}/todos`);
export const addTodo = (todo) => axios.post(`${API_URL}/todos`, todo);
export const deleteTodo = (id) => axios.delete(`${API_URL}/todos/${id}`);
export const summarizeTodos = (todos) => axios.post(`${API_URL}/todos/summarize`, { todos });
