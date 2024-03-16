import React, { ReactElement, useState } from "react";
import { Checkbox, Divider, Link as MuiLink, Switch, Typography } from "@material-ui/core";

import { usePersonalizationAndDataStyles } from "./PersonalizationAndDataStyles";
import { useGlobalStyles } from "../../../../util/globalClasses";
import { ArrowRightIcon } from "../../../../icons";
import { withDocumentTitle } from "../../../../hoc/withDocumentTitle";
import {
    ACROSS_YOUR_DEVICES,
    DATA_THROUGH_PARTNERSHIPS,
    PRIVACY_CONTROLS_FOR_TAILORED_ADS,
    TAILORED_SUGGESTIONS
} from "../../../../constants/url-constants";

const PersonalizationAndData = (): ReactElement => {
    const globalClasses = useGlobalStyles({});
    const classes = usePersonalizationAndDataStyles();
    const [checked1, setChecked1] = useState<boolean>(false);
    const [checked2, setChecked2] = useState<boolean>(true);
    const [checked3, setChecked3] = useState<boolean>(true);
    const [checked4, setChecked4] = useState<boolean>(true);
    const [checked5, setChecked5] = useState<boolean>(false);


    return (
        <div className={classes.content}>
            <Typography variant={"subtitle2"} component={"div"} className={globalClasses.itemInfoWrapper}>
                Control how Tweetgram personalizes content and collects and shares certain data.
            </Typography>
            <Divider />
            <div className={globalClasses.itemInfoWrapper}>
                <Typography variant={"body1"} component={"div"}>
                    Personalization and data
                    <span className={classes.switch}>
                        <Switch defaultChecked />
                    </span>
                </Typography>
                <Typography variant={"subtitle2"} component={"div"} className={classes.subText}>
                    This will enable or disable all of the settings on this page.
                </Typography>
            </div>
            <Divider />
            <div className={globalClasses.itemInfoWrapper}>
                <Typography variant={"h5"} component={"div"}>
                    Personalization
                </Typography>
            </div>
            <div className={globalClasses.itemInfoWrapper}>
                <div className={globalClasses.infoItemCheckbox}>
                    <Typography variant={"body1"} component={"span"}>
                        Personalized ads
                    </Typography>
                    <Checkbox checked={checked1} onChange={() => setChecked1(prevState => !prevState)} />
                </div>
                <Typography variant={"subtitle2"} component={"div"}>
                    {`You will always see ads on Tweetgram based on your Tweetgram activity. When this setting is enabled, 
                    Tweetgram may further personalize ads from Tweetgram advertisers, on and off Tweetgram, by combining your 
                    Tweetgram activity with other online activity and information from our partners. `}
                    <MuiLink href={PRIVACY_CONTROLS_FOR_TAILORED_ADS} variant="subtitle2" target="_blank"
                             rel="noopener">
                        Learn more
                    </MuiLink>
                </Typography>
            </div>
            <div className={globalClasses.itemInfoWrapper}>
                <div className={globalClasses.infoItemCheckbox}>
                    <Typography variant={"body1"} component={"span"}>
                        Personalize based on your inferred identity
                    </Typography>
                    <Checkbox checked={checked2} onChange={() => setChecked2(prevState => !prevState)} />
                </div>
                <Typography variant={"subtitle2"} component={"div"}>
                    {`Tweetgram will always personalize your experience based on information you’ve provided, as well as 
                    the devices you’ve used to log in. When this setting is enabled, Tweetgram may also personalize based 
                    on other inferences about your identity, like devices and browsers you haven’t used to log in to 
                    Tweetgram or email addresses and phone numbers similar to those linked to your Tweetgram account. `}
                    <MuiLink href={ACROSS_YOUR_DEVICES} variant="subtitle2" target="_blank" rel="noopener">
                        Learn more
                    </MuiLink>
                </Typography>
            </div>
            <div className={globalClasses.itemInfoWrapper}>
                <div className={globalClasses.infoItemCheckbox}>
                    <Typography variant={"body1"} component={"span"}>
                        Personalize based on the places you’ve been
                    </Typography>
                    <Checkbox checked={checked3} onChange={() => setChecked3(prevState => !prevState)} />
                </div>
                <Typography variant={"subtitle2"} component={"div"}>
                    {`Tweetgram always uses some information, like where you signed up and your current location, to help 
                    show you more relevant content. When this setting is enabled, Tweetgram may also personalize your 
                    experience based on other places you’ve been. `}
                </Typography>
            </div>
            <Divider />
            <div className={globalClasses.itemInfoWrapper}>
                <Typography variant={"h5"} component={"div"}>
                    Data
                </Typography>
            </div>
            <div className={globalClasses.itemInfoWrapper}>
                <div className={globalClasses.infoItemCheckbox}>
                    <Typography variant={"body1"} component={"span"}>
                        Allow use of where you see Tweetgram content across the web
                    </Typography>
                    <Checkbox checked={checked4} onChange={() => setChecked4(prevState => !prevState)} />
                </div>
                <Typography variant={"subtitle2"} component={"div"}>
                    {`This setting lets Tweetgram keep track of your visits to other websites that integrate Tweetgram 
                    content, such as embedded timelines. That information makes Tweetgram better for you, such as by 
                    personalizing your experience. This web browsing history will never be stored with your name, email, 
                    or phone number. `}
                    <MuiLink href={TAILORED_SUGGESTIONS} variant="subtitle2" target="_blank" rel="noopener">
                        Learn more
                    </MuiLink>
                </Typography>
            </div>
            <div className={globalClasses.itemInfoWrapper}>
                <div className={globalClasses.infoItemCheckbox}>
                    <Typography variant={"body1"} component={"span"}>
                        Allow additional information sharing with business partners
                    </Typography>
                    <Checkbox checked={checked5} onChange={() => setChecked5(prevState => !prevState)} />
                </div>
                <Typography variant={"subtitle2"} component={"div"}>
                    {`Tweetgram always shares information with business partners as a way to run and improve its products. 
                    When enabled, this allows Tweetgram to share additional information with those partners to help 
                    support running Tweetgram’s business, including making Tweetgram’s marketing activities on other sites 
                    and apps more relevant for you. `}
                    <MuiLink href={DATA_THROUGH_PARTNERSHIPS} variant="subtitle2" target="_blank" rel="noopener">
                        Learn more
                    </MuiLink>
                </Typography>
            </div>
            <div className={globalClasses.contentLink}>
                <Typography variant={"body1"} component={"span"}>
                    See your Tweetgram data
                </Typography>
                {ArrowRightIcon}
            </div>
        </div>
    );
};

export default withDocumentTitle(PersonalizationAndData)("Personalization and data");