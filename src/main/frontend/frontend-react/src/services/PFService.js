import axios from 'axios'

const PROGRAMMI_REST_API_URL = 'http://localhost:8080/api/programmaFedelta';
class PFService{
    getProgrammi(){
        return axios.get(PROGRAMMI_REST_API_URL);
    }
}

export default new PFService()