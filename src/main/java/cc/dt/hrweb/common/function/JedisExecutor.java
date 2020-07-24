package cc.dt.hrweb.common.function;

import cc.dt.hrweb.common.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
