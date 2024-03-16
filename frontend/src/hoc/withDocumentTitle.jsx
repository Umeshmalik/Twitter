import React, { ComponentType, useEffect } from "react";

export const withDocumentTitle = (Component) => (title) => (props) => {

    useEffect(() => {
        document.title = title ? `${title} / Twitter` : "Twitter";
    }, []);

    return <Component {...props} />;
};
