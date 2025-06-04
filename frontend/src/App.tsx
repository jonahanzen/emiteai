import React, { useState } from 'react';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Navbar from './components/NavBar';
import CreatePersonPage from './pages/CreatePersonPage';
import ListPersonPage from './pages/ListPersonPage';
import { Container, Box } from '@mui/material';
import styled from 'styled-components';
import '@fontsource/inter';

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: { main: '#90caf9' },
    secondary: { main: '#f48fb1' },
    background: { default: '#121212', paper: '#1e1e1e' },
  },
  typography: { fontFamily: 'Inter, sans-serif' },
  components: {
    MuiButton: { styleOverrides: { root: { borderRadius: 8 } } },
    MuiAppBar: { styleOverrides: { root: { borderRadius: 8 } } },
  },
});

const AppContainer = styled(Container)`
  flex: 1;
  display: flex;
  flex-direction: column;
`;

const App: React.FC = () => {
  const [currentPage, setCurrentPage] = useState<'create' | 'list'>('create');

  const handleNavigate = (page: 'create' | 'list') => setCurrentPage(page);

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <Box
        sx={{
          minHeight: '100vh',
          display: 'flex',
          flexDirection: 'column',
          backgroundColor: 'background.default',
        }}
      >
        <Navbar onNavigate={handleNavigate} />
        <AppContainer maxWidth={false}>
          {currentPage === 'create' && <CreatePersonPage />}
          {currentPage === 'list' && <ListPersonPage />}
        </AppContainer>
      </Box>
    </ThemeProvider>
  );
};

export default App;
