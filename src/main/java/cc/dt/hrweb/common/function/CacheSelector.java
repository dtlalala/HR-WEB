package cc.dt.hrweb.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
