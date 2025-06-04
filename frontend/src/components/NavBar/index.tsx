import React from 'react';
import { Button, Typography } from '@mui/material';
import { StyledAppBar, StyledToolbar, NavButtonsContainer } from './styles';

interface NavbarProps {
    onNavigate: (page: 'create' | 'list') => void;
}

const Navbar: React.FC<NavbarProps> = ({ onNavigate }) => {
    return (
        <StyledAppBar position="static">
            <StyledToolbar>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1, color: '#ffffff' }}>
                    Person Management
                </Typography>
                <NavButtonsContainer>
                    <Button color="inherit" onClick={() => onNavigate('create')}>
                        Create Person
                    </Button>
                    <Button color="inherit" onClick={() => onNavigate('list')}>
                        List Person
                    </Button>
                </NavButtonsContainer>
            </StyledToolbar>
        </StyledAppBar>
    );
};

export default Navbar;