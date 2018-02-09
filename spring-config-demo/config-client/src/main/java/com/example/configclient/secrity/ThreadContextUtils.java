package com.example.configclient.secrity;

/**
 *Create by yangwenfu on 2018/1/30
 */
public abstract class ThreadContextUtils {
	private static final ThreadLocal<ThreadContextUtils.ThreadContext> INSTANCE = new InheritableThreadLocal();

	public ThreadContextUtils() {
	}

	public static String getUserId() {
		return getContext().getUserId();
	}

	public static void setUserId(String userId) {
		getContext().setUserId(userId);
	}

	public static String getTraceId() {
		return getContext().getTraceId();
	}

	public static void setTraceId(String traceId) {
		getContext().setTraceId(traceId);
	}

	private static ThreadContextUtils.ThreadContext getContext() {
		ThreadContextUtils.ThreadContext context = (ThreadContextUtils.ThreadContext)INSTANCE.get();
		if (context == null) {
			context = new ThreadContextUtils.ThreadContext();
			INSTANCE.set(context);
		}

		return context;
	}

	public static void clear() {
		INSTANCE.remove();
	}

	private static class ThreadContext {
		private String userId;
		private String traceId;

		public ThreadContext() {
		}

		public String getUserId() {
			return this.userId;
		}

		public String getTraceId() {
			return this.traceId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public void setTraceId(String traceId) {
			this.traceId = traceId;
		}

		public boolean equals(Object o) {
			if (o == this) {
				return true;
			} else if (!(o instanceof ThreadContextUtils.ThreadContext)) {
				return false;
			} else {
				ThreadContextUtils.ThreadContext other = (ThreadContextUtils.ThreadContext)o;
				if (!other.canEqual(this)) {
					return false;
				} else {
					Object this$userId = this.getUserId();
					Object other$userId = other.getUserId();
					if (this$userId == null) {
						if (other$userId != null) {
							return false;
						}
					} else if (!this$userId.equals(other$userId)) {
						return false;
					}

					Object this$traceId = this.getTraceId();
					Object other$traceId = other.getTraceId();
					if (this$traceId == null) {
						if (other$traceId != null) {
							return false;
						}
					} else if (!this$traceId.equals(other$traceId)) {
						return false;
					}

					return true;
				}
			}
		}

		protected boolean canEqual(Object other) {
			return other instanceof ThreadContextUtils.ThreadContext;
		}

		public int hashCode() {
//			int PRIME = true;
			int result = 1;
			Object $userId = this.getUserId();
//			int result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
			Object $traceId = this.getTraceId();
			result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());
			return result;
		}

		public String toString() {
			return "ThreadContextUtils.ThreadContext(userId=" + this.getUserId() + ", traceId=" + this.getTraceId() + ")";
		}
	}
}
