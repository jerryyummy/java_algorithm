import java.util.*;

class Twitter {

    HashMap<Integer, HashSet<Integer>> followlist;
    HashMap<Integer,Integer> twitterIdOfUesr;
    Deque<Integer> twitterlist;

    public Twitter() {
        followlist = new HashMap<>();
        twitterIdOfUesr = new HashMap<>();
        twitterlist = new ArrayDeque<>();
    }

    public void postTweet(int userId, int tweetId) {
        twitterIdOfUesr.put(tweetId,userId);
        twitterlist.offer(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        int number = 10;
        List<Integer> res = new ArrayList<>();
        Iterator<Integer> iterator = twitterlist.descendingIterator();
        while(iterator.hasNext() && number>0){
            int twitterId = iterator.next();
            int Id = twitterIdOfUesr.get(twitterId);
            if(Id == userId){
                number--;
                res.add(twitterId);
                continue;
            }else if (followlist!=null && followlist.get(userId)!=null && followlist.get(userId).contains(Id)){
                number--;
                res.add(twitterId);
                continue;
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        if(!followlist.containsKey(followerId)){
            HashSet<Integer> set = new HashSet<>();
            set.add(followeeId);
            followlist.put(followerId,set);
        }else{
            followlist.get(followerId).add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if(followlist!=null){
            if (followlist.get(followerId)!=null){
                followlist.get(followerId).remove(followeeId);
            }

        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1,4);
        twitter.postTweet(2,5);
        twitter.unfollow(1,2);
        twitter.getNewsFeed(1);
    }
}
