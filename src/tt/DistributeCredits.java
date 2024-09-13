package tt;

public class DistributeCredits {

}

/*

int canDistributeCredits(int participants, vector<int> credits) {
    int MOD = 1e9 + 7;
    vector<int> dp(participants,0);
    dp[0] = 1;

    for (int x : credits) {
        int rem = x % participants;
        vector<int> new_dp = dp;
        for (int j = 0; j < participants; ++j) {
            new_dp[(j + rem) % participants] = (new_dp[(j + rem) % participants] + dp[j]) % MOD;
        }
        dp = new_dp;
    }

    return (dp[0] - 1 + MOD) % MOD;
}
 */