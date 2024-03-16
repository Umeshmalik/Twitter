import React, { FC, ReactElement, ReactNode } from "react";
import { NavLink, useLocation } from "react-router-dom";
import { Typography, useMediaQuery, Theme } from "@material-ui/core";

import { useSideMenuStyles } from "../SideMenuStyles";

interface SideMenuItemProps {
    title: string;
    path: string;
    icon: JSX.Element;
    filledIcon: JSX.Element;
    children?: ReactNode;
}

const SideMenuItem: FC<SideMenuItemProps> = ({ title, path, icon, filledIcon, children }): ReactElement => {
    const classes = useSideMenuStyles();
    const location = useLocation();
    const hidden = useMediaQuery((theme: Theme) => theme.breakpoints.down("sm"));

    return (
        <li className={classes.itemWrapper}>
            <NavLink to={path} activeClassName={"selected"}>
                <div>
                    {hidden ? null : (
                        <>
                            {children}
                            {location.pathname.includes(path) ? <span>{filledIcon}</span> : <span>{icon}</span>}
                            <Typography variant={"h5"}>{title}</Typography>
                        </>
                    )}
                </div>
            </NavLink>
        </li>
    );
};

export default SideMenuItem;
