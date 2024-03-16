import { Theme, TextFieldProps, makeStyles, createStyles } from "@material-ui/core";
import TextField from "@material-ui/core/TextField";

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            "& .MuiOutlinedInput-input": {
                paddingTop: 26,
                paddingBottom: 10
            },
            "& .MuiOutlinedInput-root": {
                "&:hover": {
                    "& fieldset": {
                        borderColor: theme.palette.grey[100]
                    }
                }
            },
            "& .MuiOutlinedInput-notchedOutline": {
                borderColor: theme.palette.grey[100],
                "&:hover": {
                    borderColor: theme.palette.grey[100]
                }
            },
            "&:hover .Mui-focused .MuiOutlinedInput-notchedOutline": {
                borderColor: theme.palette.primary.main
            },
            "& .MuiOutlinedInput-inputMultiline": {
                overflow: "hidden",
                paddingTop: 10,
                paddingBottom: 10
            }
        }
    })
);

export const TweetInputField = (props: TextFieldProps) => {
    const classes = useStyles();

    return <TextField InputProps={{ classes }}  { ...props }/>;
};
