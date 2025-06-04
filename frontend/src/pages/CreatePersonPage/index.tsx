import React, { useState, useEffect } from 'react';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import {
    Container,
    Button,
    CircularProgress,
    Paper,
    Snackbar,
    Alert
} from '@mui/material';
import {
    FormCard,
    Title,
    SectionTitle,
    StyledForm,
    Row,
    StyledTextField,
    SubmitButtonWrapper
} from './styles';
import useViaCEP from '../../hooks/useViaCep';
import { Person } from '../../types/Person';
import PersonService from '../../services/PersonService';

// Theme
const theme = createTheme({
    palette: {
        primary: { main: '#1976d2' },
        secondary: { main: '#dc004e' },
        background: { default: '#f4f6f8' },
    },
    typography: {
        fontFamily: 'Roboto, Arial, sans-serif',
        h4: { fontWeight: 700 },
    },
    spacing: 8,
});

const initialPerson: Person = {
    name: '',
    phone: '',
    cpf: '',
    address: {
        street: '', // Added street field
        number: '',
        complement: '',
        cep: '',
        neighborhood: '',
        cityName: '',
        stateName: '',
    },
};

const CreatePersonPage = () => {
    const [person, setPerson] = useState<Person>(initialPerson);
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [snackbar, setSnackbar] = useState<{ open: boolean; message: string; severity: 'success' | 'error' }>(
        { open: false, message: '', severity: 'success' }
    );
    const { fetchAddress, address: viaCepAddress, loading: viaCepLoading, error: viaCepError } = useViaCEP();

    useEffect(() => {
        if (viaCepAddress) {
            setPerson((prev) => ({
                ...prev,
                address: {
                    ...prev.address,
                    street: viaCepAddress.logradouro || '', // Set street from ViaCEP
                    neighborhood: viaCepAddress.bairro || '',
                    cityName: viaCepAddress.localidade || '',
                    stateName: viaCepAddress.uf || '',
                },
            }));
        }
    }, [viaCepAddress]);

    useEffect(() => {
        if (viaCepError) {
            setSnackbar({ open: true, message: `ViaCEP: ${viaCepError}`, severity: 'error' });
        }
    }, [viaCepError]);

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        if (name in person.address) {
            setPerson((prev) => ({
                ...prev,
                address: {
                    ...prev.address,
                    [name]: value,
                },
            }));
        } else {
            setPerson((prev) => ({ ...prev, [name]: value }));
        }
    };

    const handleCepChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const cepValue = event.target.value;
        setPerson((prev) => ({
            ...prev,
            address: {
                ...prev.address,
                cep: cepValue,
            },
        }));
        if (cepValue.replace(/\D/g, '').length === 8) {
            fetchAddress(cepValue);
        }
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setIsSubmitting(true);
        try {
            if (!person.name || !person.cpf || !person.address.cep) {
                setSnackbar({ open: true, message: 'Por favor, preencha todos os campos obrigatórios.', severity: 'error' });
                setIsSubmitting(false);
                return;
            }
            await PersonService.createPerson(person);
            setSnackbar({ open: true, message: 'Pessoa criada com sucesso!', severity: 'success' });
            setPerson(initialPerson);
        } catch {
            setSnackbar({ open: true, message: 'Falha ao criar pessoa. Tente novamente.', severity: 'error' });
        } finally {
            setIsSubmitting(false);
        }
    };

    const handleCloseSnackbar = () => {
        setSnackbar((prev) => ({ ...prev, open: false }));
    };

    return (
        <ThemeProvider theme={theme}>
            <Paper elevation={0} sx={{ bgcolor: 'background.default', minHeight: '100vh', py: 3, display: 'flex', justifyContent: 'center', alignItems: 'flex-start' }}>
                <Container maxWidth="md">
                    <FormCard elevation={3}>
                        <Title variant="h4" as="h1">
                            Cadastrar Nova Pessoa
                        </Title>
                        <StyledForm onSubmit={handleSubmit} noValidate>
                            <SectionTitle variant="h6">Dados Pessoais</SectionTitle>
                            <Row>
                                <StyledTextField label="Nome Completo" name="name" value={person.name} onChange={handleChange} required autoFocus />
                                <StyledTextField label="CPF" name="cpf" value={person.cpf} onChange={handleChange} required />
                                <StyledTextField label="Telefone (Opcional)" name="phone" value={person.phone} onChange={handleChange} />
                            </Row>
                            <SectionTitle variant="h6">Endereço</SectionTitle>
                            <Row>
                                <StyledTextField
                                    label="CEP"
                                    name="cep"
                                    value={person.address.cep}
                                    onChange={handleCepChange}
                                    required
                                    InputProps={{ endAdornment: viaCepLoading ? <CircularProgress size={20} /> : null }}
                                />
                                <StyledTextField label="Rua" name="street" value={person.address.street} onChange={handleChange} required />
                                <StyledTextField label="Número" name="number" value={person.address.number} onChange={handleChange} />
                                <StyledTextField label="Complemento (Opcional)" name="complement" value={person.address.complement} onChange={handleChange} />
                            </Row>
                            <Row>
                                <StyledTextField label="Bairro" name="neighborhood" value={person.address.neighborhood} onChange={handleChange} required InputLabelProps={{ shrink: !!person.address.neighborhood }} />
                                <StyledTextField label="Cidade" name="cityName" value={person.address.cityName} onChange={handleChange} required InputLabelProps={{ shrink: !!person.address.cityName }} />
                                <StyledTextField label="Estado (UF)" name="stateName" value={person.address.stateName} onChange={handleChange} required InputLabelProps={{ shrink: !!person.address.stateName }} />
                            </Row>
                            <SubmitButtonWrapper>
                                <Button type="submit" color="primary" disabled={isSubmitting || viaCepLoading} startIcon={isSubmitting ? <CircularProgress size={20} color="inherit" /> : null}>
                                    {isSubmitting ? 'Salvando...' : 'Salvar Pessoa'}
                                </Button>
                            </SubmitButtonWrapper>
                        </StyledForm>
                    </FormCard>
                </Container>
            </Paper>
            <Snackbar open={snackbar.open} autoHideDuration={6000} onClose={handleCloseSnackbar} anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}>
                <Alert onClose={handleCloseSnackbar} severity={snackbar.severity as 'success' | 'error'} sx={{ width: '100%' }}>
                    {snackbar.message}
                </Alert>
            </Snackbar>
        </ThemeProvider>
    );
};

export default CreatePersonPage;
