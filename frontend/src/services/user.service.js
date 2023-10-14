import axios from 'axios';
import authHeader from './auth-header';

const API_URL = '/api';

class UserService {
    getPublicContent() {
        return axios.get(API_URL + '/all');
    }

    getUserBoard() {
        return axios.get(API_URL + '/user', { headers: authHeader() });
    }

    getModeratorBoard() {
        return axios.get(API_URL + '/mod', { headers: authHeader() });
    }

    getAdminBoard() {
        return axios.get(API_URL + '/admin', { headers: authHeader() });
    }

    getTesseraByClienteId(clienteId) {
        return axios.get(API_URL + `/tessera/cliente/${clienteId}`, { headers: authHeader() })
    }

    getIscrizioniByTessera(tesseraId) {
        return axios.get(API_URL + `/tessera/${tesseraId}/iscrizioni`, { headers: authHeader() })
    }
}

export default new UserService();