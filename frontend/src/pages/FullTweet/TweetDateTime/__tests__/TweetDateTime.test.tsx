import React from "react";
import usLang from "date-fns/locale/en-US/index";
import format from "date-fns/format";

import { createMockRootState, mountWithStore } from "../../../../util/test-utils/test-helper";
import { LoadingStatus } from "../../../../types/common";
import { mockFullTweet } from "../../../../util/test-utils/mock-test-data";
import TweetDateTime from "../TweetDateTime";

describe("TweetDateTime", () => {
    it("should render correctly", () => {
        const wrapper = mountWithStore(<TweetDateTime />, createMockRootState(LoadingStatus.SUCCESS));
        expect(wrapper.text().includes(`${format(new Date(mockFullTweet.dateTime), "hh:mm a", { locale: usLang })}`)).toBe(true);
        expect(wrapper.text().includes(`${format(new Date(mockFullTweet.dateTime), " MMM dd, yyyy")} · Twitter Web App`)).toBe(true);
    });
});
