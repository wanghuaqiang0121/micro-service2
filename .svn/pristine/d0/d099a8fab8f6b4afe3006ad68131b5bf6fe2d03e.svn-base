package org.service.tools.feature;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月17日
 * @description: AtomicFloat
 */
public class AtomicFloat extends Number {

	private static final long serialVersionUID = 7920926950938089199L;
	private AtomicInteger bits;

	public AtomicFloat() {
		this(0f);
	}

	public AtomicFloat(float initialValue) {
		bits = new AtomicInteger(Float.floatToIntBits(initialValue));
	}

	// 叠加
	public final float addAndGet(float delta) {
		float expect;
		float update;
		do {
			expect = get();
			update = expect + delta;
		} while (!this.compareAndSet(expect, update));

		return update;
	}

	public final float getAndAdd(float delta) {
		float expect;
		float update;
		do {
			expect = get();
			update = expect + delta;
		} while (!this.compareAndSet(expect, update));

		return expect;
	}

	public final float getAndDecrement() {
		return getAndAdd(-1);
	}

	public final float decrementAndGet() {
		return addAndGet(-1);
	}

	public final float getAndIncrement() {
		return getAndAdd(1);
	}

	public final float incrementAndGet() {
		return addAndGet(1);
	}

	public final float getAndSet(float newValue) {
		float expect;
		do {
			expect = get();
		} while (!this.compareAndSet(expect, newValue));

		return expect;
	}

	public final boolean compareAndSet(float expect, float update) {
		return bits.compareAndSet(Float.floatToIntBits(expect), Float.floatToIntBits(update));
	}

	public final void set(float newValue) {
		bits.set(Float.floatToIntBits(newValue));
	}

	public final float get() {
		return Float.intBitsToFloat(bits.get());
	}

	@Override
	public float floatValue() {
		return get();
	}

	@Override
	public double doubleValue() {
		return (double) floatValue();
	}

	@Override
	public int intValue() {
		return (int) get();
	}

	@Override
	public long longValue() {
		return (long) get();
	}

	@Override
	public String toString() {
		return Float.toString(get());
	}
}