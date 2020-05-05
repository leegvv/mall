package net.arver.mall.security.service.impl;

import net.arver.mall.security.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis操作实现类.
 */
@Service
public class RedisServiceImpl implements RedisService {

    /**
     * redisTemplate
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(final String key, final Object value, final long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void set(final String key, final Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean del(final String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long del(final List<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public Boolean expire(final String key, final long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public Boolean hasKey(final String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long incr(final String key, final long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long decr(final String key, final long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public Object hGet(final String key, final String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Boolean hSet(final String key, final String hashKey, final Object value, final long time) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }

    @Override
    public void hSet(final String key, final String hashKey, final Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Map<Object, Object> hGetAll(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Boolean hSetAll(final String key, final Map<String, Object> map, final long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }

    @Override
    public void hSetAll(final String key, final Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hDel(final String key, final Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hHasKey(final String key, final String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Long hIncr(final String key, final String hashKey, final Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Long hDecr(final String key, final String hashKey, final Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    public Set<Object> sMembers(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Long sAdd(final String key, final Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long sAdd(final String key, final long time, final Object... values) {
        final Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Boolean sIsMember(final String key, final Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public Long sSize(final String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Long sRemove(final String key, final Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public List<Object> lRange(final String key, final long Start, final long end) {
        return redisTemplate.opsForList().range(key, Start, end);
    }

    @Override
    public Long lSize(final String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public Object lIndex(final String key, final long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public Long lPush(final String key, final Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long lPush(final String key, final Object value, final long time) {
        final Long count = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return count;
    }

    @Override
    public Long lPushAll(final String key, final Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public Long lPushAll(final String key, final Long time, final Object... values) {
        final Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Long lRemove(final String key, final long count, final Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
}
