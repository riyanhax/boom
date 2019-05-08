package com.ys.project.projectDTO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO3 {
	// noticeBoard ó��
	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int total;
	private Criteria3 cri3;

	public PageDTO3(Criteria3 cri3, int total) {
		this.cri3 = cri3;
		this.total = total;

		// ���� �������� 11 ��������� �Ѵٸ� 2 * 6 = 12�� ������ �������� ������ �ȴ�
		this.endPage = (int) (Math.ceil(cri3.getPageNum() / 10.0)) * 10;

		this.startPage = this.endPage - 9; // ����� 11 �� �ǰ�

		// �� 112 ���̰� �ִٰ� �Ѵٸ� 12������ ���� ������ ��������� �˼� �ִ�.
		int realEnd = (int) (Math.ceil((total * 1.0) / cri3.getAmount()));

		// ���� ������ �������� 20 �ε� ���� ������ �������� �۴ٸ� �� ������ �������ش�.
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		// ���� ���̰� 1 �̻� ù���̰� �ƴ϶�� ��� ������ ���� �ִٴ� ���̴�. true
		this.prev = this.startPage > 1;

		// ���� ������ �������� �ȿ´ٸ� �������� �ѱ�� �ִ�. true
		this.next = this.endPage < realEnd;

	}
}
