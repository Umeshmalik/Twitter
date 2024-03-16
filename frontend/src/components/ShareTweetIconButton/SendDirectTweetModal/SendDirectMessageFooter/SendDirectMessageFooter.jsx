import React, { FC, memo, ReactElement } from "react";
import IconButton from "@material-ui/core/IconButton";
import { useDispatch } from "react-redux";

import { useSendDirectTweetModalStyles } from "../SendDirectTweetModalStyles";
import { SendDirectMessageInput } from "../SendDirectMessageInput";
import { SendMessageIcon } from "../../../../icons";
import { addChatMessageWithTweet } from "../../../../store/ducks/chatMessages/actionCreators";
import { UserResponse } from "../../../../types/user";
import { useInputText } from "../../../../hook/useInputText";

// interface SendDirectMessageFooterProps {
//     tweetId: number;
//     selectedUsers: UserResponse[];
//     onSendMessageFinish: () => void;
// }

const SendDirectMessageFooter = (
    {
        tweetId,
        selectedUsers,
        onSendMessageFinish
    }
) => {
    const classes = useSendDirectTweetModalStyles();
    const dispatch = useDispatch();
    const { text, setText, handleChangeText } = useInputText();

    const handleClickSendMessage = () => {
        dispatch(addChatMessageWithTweet({
            text,
            tweetId,
            usersIds: selectedUsers.map((user) => user.id)
        }));
        setText("");
        onSendMessageFinish();
    };

    return (
        <div className={classes.footer}>
            <SendDirectMessageInput
                multiline
                value={text}
                onChange={handleChangeText}
                variant="outlined"
                placeholder="Add a comment"
            />
            <div className={classes.chatIcon}>
                <IconButton
                    onClick={handleClickSendMessage}
                    disabled={selectedUsers.length === 0}
                    color="primary"
                    size="small"
                >
                    <>{SendMessageIcon}</>
                </IconButton>
            </div>
        </div>
    );
};

export default memo(SendDirectMessageFooter);
