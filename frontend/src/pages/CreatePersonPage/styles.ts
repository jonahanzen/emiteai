import { styled } from '@mui/material/styles';
import { Typography, Paper, TextField, Box } from '@mui/material';

export const FormCard = styled(Paper)(({ theme }) => ({
    marginTop: theme.spacing(4),
    marginBottom: theme.spacing(4),
    padding: theme.spacing(3),
    backgroundColor: '#fff',
    borderRadius: '8px',
}));

export const Title = styled(Typography)(({ theme }) => ({
    marginBottom: theme.spacing(3),
    color: theme.palette.primary.main,
    textAlign: 'center',
    fontWeight: 700,
}));

export const SectionTitle = styled(Typography)(({ theme }) => ({
    marginTop: theme.spacing(3),
    marginBottom: theme.spacing(2),
    color: theme.palette.secondary.main,
    borderBottom: `2px solid ${theme.palette.secondary.light}`,
    paddingBottom: theme.spacing(0.5),
    fontWeight: 500,
}));

export const StyledForm = styled('form')({
    width: '100%',
});

export const Row = styled(Box)(({ theme }) => ({
    display: 'flex',
    flexWrap: 'wrap',
    gap: theme.spacing(2),
    marginBottom: theme.spacing(2),
}));

export const StyledTextField = styled(TextField)(({ theme }) => ({
    '& .MuiOutlinedInput-root': {
        borderRadius: '8px',
    },
    '& .MuiInputLabel-root': {
        fontWeight: 500,
    },
    flex: 1,
    minWidth: 180,
}));

export const SubmitButtonWrapper = styled('div')(({ theme }) => ({
    marginTop: theme.spacing(3),
    display: 'flex',
    justifyContent: 'flex-end',
}));
