import axios from 'axios';
import { Person } from '../types/Person';
import { API_BASE_URL } from '../constants/api';
import { log } from 'console';

const PersonService = {
    async createPerson(personData: Person): Promise<Person> {
        try {
            const response = await axios.post<Person>(`${API_BASE_URL}/person`, personData);
            return response.data;
        } catch (error: any) {
            console.error('Error creating person:', error);
            throw error;
        }
    },

    async findAll(): Promise<Person[]> {
        try {
            const response = await axios.get<Person[]>(`${API_BASE_URL}/person`);
            console.log('Fetched persons:', response);
            return response.data;
        } catch (error: any) {
            console.error('Error fetching persons:', error);
            throw error;
        }
    },
}

export default PersonService;