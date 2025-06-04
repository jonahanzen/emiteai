import PersonService from './PersonService';
import axios from 'axios';
import { Person } from '../types/Person';

jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

describe('PersonService', () => {
    const person: Person = {
        name: 'Test User',
        phone: '1234567890',
        cpf: '12345678901',
        address: {
            number: '123',
            complement: '',
            cep: '12345678',
            neighborhood: 'Centro',
            cityName: 'City',
            stateName: 'ST',
            street: 'Main St',
        },
    };

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should create a person', async () => {
        mockedAxios.post.mockResolvedValueOnce({ data: person });
        const result = await PersonService.createPerson(person);
        expect(result).toEqual(person);
        expect(mockedAxios.post).toHaveBeenCalledWith(expect.stringContaining('/person'), person);
    });

    it('should fetch all persons', async () => {
        mockedAxios.get.mockResolvedValueOnce({ data: [person] });
        const result = await PersonService.findAll();
        expect(result).toEqual([person]);
        expect(mockedAxios.get).toHaveBeenCalledWith(expect.stringContaining('/person'));
    });

    it('should throw error on createPerson failure', async () => {
        mockedAxios.post.mockRejectedValueOnce(new Error('fail'));
        await expect(PersonService.createPerson(person)).rejects.toThrow('fail');
    });

    it('should throw error on findAll failure', async () => {
        mockedAxios.get.mockRejectedValueOnce(new Error('fail'));
        await expect(PersonService.findAll()).rejects.toThrow('fail');
    });
});
