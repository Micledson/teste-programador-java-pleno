import axios from "axios";

export const API = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 10000,
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
        'Access-Control-Allow-Credentials': 'true'
    }
});