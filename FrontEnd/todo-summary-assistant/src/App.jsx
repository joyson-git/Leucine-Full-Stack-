// src/App.jsx
import React, { useState, useEffect } from 'react';
import { fetchTodos, addTodo, deleteTodo, summarizeTodos } from './services/api';
import './App.css';

function App() {
  const [todos, setTodos] = useState([]);
  const [todoText, setTodoText] = useState('');
  const [notification, setNotification] = useState('');

  useEffect(() => {
    fetchTodos().then(response => {
      setTodos(response.data);
    });
  }, []);

  const handleAddTodo = () => {
    if (todoText.trim() !== '') {
      addTodo({ text: todoText, completed: false }).then(response => {
        setTodos([...todos, response.data]);
        setTodoText('');
      });
    }
  };

  const handleDeleteTodo = (id) => {
    deleteTodo(id).then(() => {
      setTodos(todos.filter(todo => todo.id !== id));
    });
  };

  const handleSummarizeTodos = () => {
    summarizeTodos(todos.map(todo => todo.text))
      .then(() => {
        setNotification('Summary sent to Slack successfully.');
      })
      .catch(() => {
        setNotification('Failed to send summary.');
      });
  };

  return (
    <div className="App">
      <h1>Todo Summary Assistant</h1>
      <div className="add-todo">
        <input
          type="text"
          value={todoText}
          onChange={(e) => setTodoText(e.target.value)}
          placeholder="Enter your todo..."
        />
        <button onClick={handleAddTodo}>Add</button>
      </div>
      <div className="todo-list">
        {todos.map((todo) => (
          <div key={todo.id} className="todo-item">
            <input
              type="checkbox"
              checked={todo.completed}
              onChange={() => {}}
            />
            <span style={{ textDecoration: todo.completed ? 'line-through' : 'none' }}>
              {todo.text}
            </span>
            <button onClick={() => handleDeleteTodo(todo.id)}>🗑️</button>
          </div>
        ))}
      </div>
      <div className="generate-summary">
        <button onClick={handleSummarizeTodos}>Generate Summary & Send to Slack</button>
      </div>
      {notification && (
        <div className={`notification ${notification.includes('successfully') ? 'success' : 'error'}`}>
          {notification}
        </div>
      )}
      <footer>
        Built with ❤️ using React, Spring Boot, MySQL & OpenAI
      </footer>
    </div>
  );
}

export default App;
