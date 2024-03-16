import { Theme, makeStyles, createStyles, TextFieldProps } from "@material-ui/core";
import TextField from "@material-ui/core/TextField/TextField";


const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            "& .MuiOutlinedInput-root": {
                "&.Mui-focused": {
                    "& svg path": {
                        fill: theme.palette.primary.main
                    }
                },
                "& fieldset": {
                    border: 0
                },
                "& .MuiInputAdornment-root": {
                    "& svg": {
                        color: theme.palette.text.secondary,
                        height: "1.25em"
                    }
                }
            },
            "& .MuiOutlinedInput-input": {
                padding: "12px 14px 14px 5px"
            }
        }
    })
);

export const ModalInputWrapper = (props: TextFieldProps) => {
    const classes = useStyles();

    return <TextField InputProps={{ classes }}  { ...props }/>;
};
