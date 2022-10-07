package cn.imadc.application.base.samples.proxy;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-09-08
 */
public class ActualClass implements IAction {

    public ActualClass() {
    }

    @Override
    public void hh() {
        System.out.println("hh");
    }

    @Override
    public void hh2() {
        System.out.println("hh2");
    }
}
