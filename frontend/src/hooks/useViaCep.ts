// src/hooks/useViaCEP.ts

import { useState, useCallback } from 'react';
import axios from 'axios';
import { ViaCEPAddress } from '../types/ViaCep'; // Importa a interface ViaCEPAddress

interface UseViaCEPResult {
    address: ViaCEPAddress | null;
    loading: boolean;
    error: string | null;
    fetchAddress: (cep: string) => void;
}

const useViaCEP = (): UseViaCEPResult => {
    const [address, setAddress] = useState<ViaCEPAddress | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);

    const fetchAddress = useCallback(async (cep: string) => {
        // Remove caracteres não numéricos do CEP
        const cleanCep = cep.replace(/\D/g, '');

        if (cleanCep.length !== 8) {
            setAddress(null);
            setError('CEP must have 8 digits.');
            setLoading(false);
            return;
        }

        setLoading(true);
        setError(null);

        try {
            const response = await axios.get<ViaCEPAddress>(`https://viacep.com.br/ws/${cleanCep}/json/`);

            if (response.data.erro) {
                setAddress(null);
                setError('CEP not found or invalid.');
            } else {
                setAddress(response.data);
            }
        } catch (err) {
            console.error('Error fetching CEP:', err);
            setError('Failed to fetch address. Please try again.');
            setAddress(null);
        } finally {
            setLoading(false);
        }
    }, []);

    return { address, loading, error, fetchAddress };
};

export default useViaCEP;