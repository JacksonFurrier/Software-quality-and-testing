package ucoach.util;

/**
 * Created by szucs on 2/9/2017.
 */

public interface IDelegate {

    public Object invoke(Object[] args);

    public Object invoke(Object arg);

    public Object invoke(Object arg1, Object arg2);

    public Object invoke();
}
