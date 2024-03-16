import React, { FC, ReactElement } from "react";
import { Button, Theme, useMediaQuery } from "@material-ui/core";
import CreateIcon from "@material-ui/icons/Create";

import AddTweetModal from "../../AddTweetModal/AddTweetModal";
import { useModalWindow } from "../../../hook/useModalWindow";
import { useSideMenuStyles } from "../SideMenuStyles";

const AddTweetButton: FC = (): ReactElement => {
    const classes = useSideMenuStyles();
    const { visibleModalWindow, onOpenModalWindow, onCloseModalWindow } = useModalWindow();
    const hiddenSmDown = useMediaQuery((theme: Theme) => theme.breakpoints.down("sm"));
    const hiddenMdUp = useMediaQuery((theme: Theme) => theme.breakpoints.up("md"));

    return (
        <li className={classes.itemWrapper}>
            <Button
                onClick={onOpenModalWindow}
                className={classes.button}
                variant="contained"
                color="primary"
                fullWidth
            >
                {hiddenSmDown ? null : "Tweet"}
                {hiddenMdUp ? null : <CreateIcon />}
            </Button>
            <AddTweetModal visible={visibleModalWindow} onClose={onCloseModalWindow} />
        </li>
    );
};

export default AddTweetButton;
