package com.ys.project.projectDTO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO2 {
	// noticeBoard ó��
	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int total;
	private Criteria2 cri;

	public PageDTO2(Criteria2 cri2, int total) {
		this.cri = cri2;
		this.total = total;

		// ���� �������� 11 ��������� �Ѵٸ� 2 * 6 = 12�� ������ �������� ������ �ȴ�
		this.endPage = (int) (Math.ceil(cri2.getPageNum() / 10.0)) * 10;

		this.startPage = this.endPage - 9; // ����� 11 �� �ǰ�

		// �� 112 ���̰� �ִٰ� �Ѵٸ� 12������ ���� ������ ��������� �˼� �ִ�.
		int realEnd = (int) (Math.ceil((total * 1.0) / cri2.getAmount()));

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
