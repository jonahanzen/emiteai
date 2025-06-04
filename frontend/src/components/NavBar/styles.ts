import { AppBar, Toolbar } from '@mui/material';
import styled from 'styled-components';

export const StyledAppBar = styled(AppBar)`
  background-color: #212121; // Dark background similar to the image
`;

export const StyledToolbar = styled(Toolbar)`
  display: flex;
  justify-content: space-between;
`;

export const NavButtonsContainer = styled.div`
  display: flex;
  gap: 16px; // Space between buttons
`;