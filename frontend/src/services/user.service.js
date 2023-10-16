import axios from 'axios';
import authHeader from './auth-header';

const API_URL = '/api';

class UserService {

    getUserBoard() {
        return axios.get(API_URL + '/user', { headers: authHeader() });
    }

    getIscrizioniByCliente(tesseraId) {
        return axios.get(API_URL + `/tessera/${tesseraId}/iscrizioni`, { headers: authHeader() })
    }
}

export default new UserService();