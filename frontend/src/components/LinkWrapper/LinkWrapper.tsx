import React, { FC, ReactElement, ReactNode } from "react";
import { useHistory } from "react-router-dom";

import { useGlobalStyles } from "../../util/globalClasses";

interface LinkWrapperProps {
    children: ReactNode;
    path: string;
    visiblePopperWindow?: boolean;
}

const LinkWrapper: FC<LinkWrapperProps> = ({ children, path, visiblePopperWindow }): ReactElement => {
    const globalClasses = useGlobalStyles({});
    const history = useHistory();

    if (visiblePopperWindow) {
        return <span>{children}</span>;
    } else {
        return (
            <div className={globalClasses.link} onClick={() => history.push(path)}>
                {children}
            </div>
        );
    }
};

export default LinkWrapper;
