package com.krupa;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum LockStrategy {
	UNLOCKED {
		@Override
		public final void prepare() {
			prepareImpl();
		}
		
		@Override
		public final int update() {
			return updateImpl();
		}
	},
	SYNC_METHOD {
		@Override
		public final synchronized void prepare() {
			prepareImpl();
		}
		
		@Override
		public final synchronized int update() {
			return updateImpl();
		}
	},
	SYNC_BLOCK {
		@Override
		public final void prepare() {
			synchronized ( this ) {
				prepareImpl();
			}
		}
		
		@Override
		public final int update() {
			synchronized ( this ) {
				return updateImpl();
			}
		}
	},
	LOCK_OBJ {
		private final Lock lock = new ReentrantLock();
		
		@Override
		public final void prepare() {
			lock.lock();
			try {
				prepareImpl();
			} finally {
				lock.unlock();
			}
		}
		
		@Override
		public final int update() {
			lock.lock();
			try {
				return updateImpl();
			} finally {
				lock.unlock();
			}
		}
	};
	
	final LinkedList<Object> queue = new LinkedList<Object>();
	
	public abstract void prepare();
	
	public abstract int update();
	
	protected final void prepareImpl() {
		Object obj1 = new Object();
		obj1.hashCode();
		
		Object obj2 = new Object();
		obj2.hashCode();

		queue.addLast(obj1);
		queue.addLast(obj2);
	}
	
	protected final int updateImpl() {
		Object out = queue.removeFirst();
		queue.addLast(out);
		return out.hashCode();
	}
}