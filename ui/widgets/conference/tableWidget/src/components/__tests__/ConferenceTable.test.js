import React from 'react';
import '@testing-library/jest-dom/extend-expect';
import { fireEvent, render } from '@testing-library/react';
import 'components/__mocks__/i18n';
import conferenceMocks from 'components/__mocks__/conferenceMocks';
import ConferenceTable from 'components/ConferenceTable';

describe('ConferenceTable', () => {
  it('shows conferences', () => {
    const { getByText } = render(<ConferenceTable items={conferenceMocks} />);
    expect(
      getByText(
        'Perferendis et consequuntur voluptates architecto excepturi esse praesentium molestiae dicta. Aut ea mollitia veritatis accusantium. Reiciendis dolorum natus qui omnis nostrum qui ut. Incidunt aut qui provident. Et enim magni maxime aut nam molestias vel.'
      )
    ).toBeInTheDocument();
    expect(
      getByText(
        'Quia nihil enim. Et voluptatem similique numquam. Est dolores voluptas rerum incidunt beatae est officiis. Id tempora nihil non nesciunt quos quasi impedit.'
      )
    ).toBeInTheDocument();
  });

  it('shows no conferences message', () => {
    const { queryByText } = render(<ConferenceTable items={[]} />);
    expect(
      queryByText(
        'Perferendis et consequuntur voluptates architecto excepturi esse praesentium molestiae dicta. Aut ea mollitia veritatis accusantium. Reiciendis dolorum natus qui omnis nostrum qui ut. Incidunt aut qui provident. Et enim magni maxime aut nam molestias vel.'
      )
    ).not.toBeInTheDocument();
    expect(
      queryByText(
        'Quia nihil enim. Et voluptatem similique numquam. Est dolores voluptas rerum incidunt beatae est officiis. Id tempora nihil non nesciunt quos quasi impedit.'
      )
    ).not.toBeInTheDocument();

    expect(queryByText('entities.conference.noItems')).toBeInTheDocument();
  });

  it('calls onSelect when the user clicks a table row', () => {
    const onSelectMock = jest.fn();
    const { getByText } = render(
      <ConferenceTable items={conferenceMocks} onSelect={onSelectMock} />
    );
    fireEvent.click(
      getByText(
        'Perferendis et consequuntur voluptates architecto excepturi esse praesentium molestiae dicta. Aut ea mollitia veritatis accusantium. Reiciendis dolorum natus qui omnis nostrum qui ut. Incidunt aut qui provident. Et enim magni maxime aut nam molestias vel.'
      )
    );
    expect(onSelectMock).toHaveBeenCalledTimes(1);
  });
});
