import { makeStyles, Theme } from "@material-ui/core";

export const useSideMenuHomeItemStyles = makeStyles((theme: Theme) => ({
    homeNotification: {
        position: "absolute",
        marginLeft: 20,
        marginBottom: 25,
        width: 6,
        height: 6,
        borderRadius: "50%",
        backgroundColor: theme.palette.primary.main
    }
}));
