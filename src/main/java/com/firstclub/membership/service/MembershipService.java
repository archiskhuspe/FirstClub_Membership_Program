package com.firstclub.membership.service;

import com.firstclub.membership.dto.*;

public interface MembershipService {
    MembershipStatusResponse getStatus(Long userId);
    SubscribeResponse subscribe(SubscribeRequest request);
    UpgradeResponse upgrade(UpgradeRequest request);
    DowngradeResponse downgrade(DowngradeRequest request);
    CancelResponse cancel(CancelRequest request);
    RenewResponse renew(RenewRequest request);
} 