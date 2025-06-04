import React, { useEffect, useState } from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    IconButton,
    Collapse,
    Box,
    Typography,
} from '@mui/material';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import PersonService from '../../services/PersonService';
import { Person } from '../../types/Person';

const Row: React.FC<{ person: Person }> = ({ person }) => {
    const [open, setOpen] = useState(false);
    return (
        <>
            <TableRow>
                <TableCell>
                    <IconButton size="small" onClick={() => setOpen(!open)}>
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </TableCell>
                <TableCell>{person.name}</TableCell>
                <TableCell>{person.cpf}</TableCell>
                <TableCell>{person.phone}</TableCell>
            </TableRow>
            <TableRow>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={4}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box margin={1}>
                            <Typography variant="subtitle1" gutterBottom>
                                Endereço
                            </Typography>
                            <Typography variant="body2">
                                {person.address.street}, {person.address.number}, {person.address.complement || ''}<br />
                                {person.address.neighborhood} - {person.address.cityName}/{person.address.stateName}<br />
                                CEP: {person.address.cep}
                            </Typography>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </>
    );
};

const ListPersonPage: React.FC = () => {
    const [persons, setPersons] = useState<Person[]>([]);

    useEffect(() => {
        PersonService.findAll().then(setPersons);
    }, []);

    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell />
                        <TableCell>Nome</TableCell>
                        <TableCell>CPF</TableCell>
                        <TableCell>Telefone</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {persons.map((person, idx) => (
                        <Row key={person.cpf + idx} person={person} />
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default ListPersonPage;
