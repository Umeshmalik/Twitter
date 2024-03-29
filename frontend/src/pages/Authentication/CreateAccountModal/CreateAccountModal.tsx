import React, { FC, ReactElement, useState } from "react";
import { Button, Link as MuiLink, Typography } from "@material-ui/core";

import { useCreateAccountModalStyles } from "./CreateAccountModalStyles";
import { RegistrationInputField } from "../RegistrationInput/RegistrationInputField";
import Spinner from "../../../components/Spinner/Spinner";
import { TWITTER_COOKIES, TWITTER_PRIVACY, TWITTER_TOS_NEW } from "../../../constants/url-constants";
import { RegistrationRequest } from "../../../types/auth";
import { RegistrationApi } from "../../../services/api/user-service/registrationApi";
import DialogWrapper from "../DialogWrapper/DialogWrapper";

interface CustomizeModalProps {
    isOpen: boolean;
    onClose: () => void;
    registrationInfo: RegistrationRequest;
    onOpenEmailVerification: () => void;
}

const CreateAccountModal: FC<CustomizeModalProps> = (
    {
        isOpen,
        onClose,
        registrationInfo,
        onOpenEmailVerification
    }
): ReactElement => {
    const classes = useCreateAccountModalStyles();
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const onSubmit = (): void => {
        setIsLoading(true);
        RegistrationApi.sendRegistrationCode(registrationInfo)
            .then(() => {
                setIsLoading(false);
                onOpenEmailVerification();
            })
            .catch((error) => {
                console.log(error.reponse);
                setIsLoading(false);
            });
    };

    return (
        <DialogWrapper isOpen={isOpen} onClose={onClose} logo={false} hideBackdrop modalShadow>
            <Typography component={"div"} className={classes.title}>
                Step 3 of 5
            </Typography>
            {isLoading ? (
                <Spinner />
            ) : (
                <>
                    <Typography variant={"h5"} component={"div"} className={classes.subtitle}>
                        Create your account
                    </Typography>
                    <div className={classes.form}>
                        <RegistrationInputField
                            label="Name"
                            variant="filled"
                            value={registrationInfo.username}
                            fullWidth
                            disabled
                        />
                        <RegistrationInputField
                            label="Email"
                            variant="filled"
                            value={registrationInfo.email}
                            fullWidth
                            disabled
                        />
                        <RegistrationInputField
                            label="Birth date"
                            variant="filled"
                            value={registrationInfo.birthday}
                            fullWidth
                            disabled
                        />
                    </div>
                    <Typography variant={"body1"} component={"div"} className={classes.text}>
                        {"By signing up, you agree to the "}
                        <MuiLink href={TWITTER_TOS_NEW} variant="body1" target="_blank" rel="noopener">
                            Terms of Service
                        </MuiLink>
                        {" and "}
                        <MuiLink href={TWITTER_PRIVACY} variant="body1" target="_blank" rel="noopener">
                            Privacy Policy
                        </MuiLink>
                        {", including "}
                        <MuiLink href={TWITTER_COOKIES} variant="body1" target="_blank" rel="noopener">
                            Cookie Use
                        </MuiLink>
                        {". Others will be able to find you by email or phone number when provided · "}
                        <MuiLink href={TWITTER_PRIVACY} variant="body1" target="_blank" rel="noopener">
                            Privacy Options
                        </MuiLink>
                    </Typography>
                    <Button onClick={onSubmit} variant="contained" color="primary" size="small" fullWidth>
                        Sign up
                    </Button>
                </>
            )}
        </DialogWrapper>
    );
};

export default CreateAccountModal;
