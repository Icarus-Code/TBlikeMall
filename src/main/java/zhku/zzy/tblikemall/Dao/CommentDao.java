package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Entity.Comment;
import zhku.zzy.tblikemall.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    public List<Comment> findByUser(int userId) {
        String sql = "select * from comment where userid=?";
        List<Comment> comments = new ArrayList<Comment>();
        Util util = new Util();
        List params = new ArrayList();
        params.add(userId);
        List<Object[]> objs = util.queryList(sql,params,5);
        for (Object[] obj : objs) {
            Comment comment = new Comment();
            comment.setCommentid((Integer)obj[0]);
            comment.setComment(obj[1].toString());
            comment.setProductid((Integer)obj[2]);
            comment.setUserid((Integer)obj[3]);
            comment.setRate((Integer)obj[4]);
            comments.add(comment);
        }
        return comments;
    }

    public List<Comment> findByProductid(int productid) {
        String sql = "select * from comments where productid=?";
        List<Comment> comments = new ArrayList<>();
        Util util = new Util();
        List params = new ArrayList();
        params.add(productid);
        List<Object[]> objs = util.queryList(sql,params,5);
        for (Object[] obj : objs) {
            Comment comment = new Comment();
            comment.setCommentid((Integer)obj[0]);
            comment.setComment(obj[1].toString());
            comment.setProductid((Integer)obj[2]);
            comment.setUserid((Integer)obj[3]);
            comment.setRate((Integer)obj[4]);
            comments.add(comment);
        }
        return comments;
    }

    public void commentAdd(Comment comment){
        String sql = "insert into comments(comment,productid,userid,rating) values(?,?,?,?)";
        Util util = new Util();
        List params = new ArrayList();
        params.add(comment.getComment());
        params.add(comment.getProductid());
        params.add(comment.getUserid());
        params.add(comment.getRate());
        util.executeUpdate(sql,params);
    }

    public void commentDelete(int commentid){
        String sql = "delete from comments where commentid = ?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(commentid);
        util.executeUpdate(sql,params);
    }
}
