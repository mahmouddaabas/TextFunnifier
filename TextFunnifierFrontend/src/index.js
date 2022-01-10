import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';

const rootElement = document.getElementById('root');
document.body.style = 'background: #FAFAFA;';
ReactDOM.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>,
    rootElement
);