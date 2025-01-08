package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.CommentDao;
import zhku.zzy.tblikemall.Entity.Comment;

import java.util.List;

public class CommentService {
    CommentDao commentDao = new CommentDao();
    public List<Comment> findByUser(int userId){
        return commentDao.findByUser(userId);
    }

    public void commentAdd(Comment comment){
        commentDao.commentAdd(comment);
    }

    public void commentDelete(int commentid){
        commentDao.commentDelete(commentid);
    }

    public List<Comment> findByProductid(int productid){
        return commentDao.findByProductid(productid);
    }
}
